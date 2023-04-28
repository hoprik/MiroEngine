from nbt import nbt

nbtfile = nbt.NBTFile("home.nbt", 'rb')

blockPosClass = []
paletteBlockClass = []

for palette in nbtfile["palette"]:
    paletteName = str(palette["Name"])
    propertiesString = ""
    try:
        properties = palette["Properties"]
        for property in properties.items():
            key = str(property[0]).upper()
            value = str(property[1]).upper()
            propertiesString += ".setValue({}, {})".format(key, value)
    except:
        pass


    paletteNameId = paletteName.split(":")
    paletteBlock = 'blockStates.add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("{}", ' \
                   '"{}")).defaultBlockState(){});'.format(paletteNameId[0], paletteNameId[1], propertiesString)
    paletteBlockClass.append(paletteBlock)

for block in nbtfile["blocks"]:
    blockPos = block["pos"]
    state = block["state"]
    classPos = "level.setBlockAndUpdate(new BlockPos(pos.getX()+{}, pos.getY()+{}, pos.getZ()+{}), blockStates.get({}));".format(
        str(blockPos[0]), str(blockPos[1]), str(blockPos[2]), str(state))
    blockPosClass.append(classPos)

classBuilder = '''
public class BuilderBuildings {
    Level level;
    BlockPos pos;
    List<BlockState> blockStates;
    public BuilderBuildings(Level level, BlockPos pos) {
        this.level = level;
        this.pos = pos;
        this.blockStates = palette();
        build();
    }

    public List<BlockState> palette(){
        List<BlockState> blockStates = new ArrayList<>();
        
'''

for paletteBlock in paletteBlockClass:
    classBuilder += "\t\t" + paletteBlock + "\n"

classBuilder += '''
        return blockStates;
    }

    public void build(){
    
'''
for blockPos in blockPosClass:
    classBuilder += "\t\t" + blockPos + "\n"
classBuilder += '''
    }
}
'''

print(classBuilder)
