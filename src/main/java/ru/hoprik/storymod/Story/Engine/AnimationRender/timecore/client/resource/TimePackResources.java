package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.client.resource;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import org.jetbrains.annotations.NotNull;
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.client.resource.GlobalResourceStorage;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

public class TimePackResources implements PackResources {
    @NotNull
    @Override
    public InputStream getRootResource(@NotNull String fileName) {
        throw new UnsupportedOperationException("TimeCore ResourcePacks can't have root resources.");
    }

    @Override
    public InputStream getResource(PackType type, ResourceLocation location) throws IOException {
        if (type == PackType.CLIENT_RESOURCES) {
            return GlobalResourceStorage.INSTANCE.getResource(location);
        } else {
            throw new UnsupportedOperationException("TimeCore ResourcePacks supports only client resources.");
        }
    }

    @Override
    public Collection<ResourceLocation> getResources(PackType type, String namespace, String path, Predicate<ResourceLocation> filter) {
        if (type == PackType.CLIENT_RESOURCES) {
            return GlobalResourceStorage.INSTANCE.getResources(namespace, path, filter);
        }

        return Collections.emptyList();
    }

    @Override
    public boolean hasResource(@NotNull PackType type, @NotNull ResourceLocation location) {
        return type == PackType.CLIENT_RESOURCES && GlobalResourceStorage.INSTANCE.hasResource(location);
    }

    @NotNull
    @Override
    public Set<String> getNamespaces(@NotNull PackType type) {
        return type == PackType.CLIENT_RESOURCES ? GlobalResourceStorage.INSTANCE.getDomains() : Collections.emptySet();
    }

    @Nullable
    @Override
    public <T> T getMetadataSection(MetadataSectionSerializer<T> deserializer_) throws IOException {
        return null;
    }

    @NotNull
    @Override
    public String getName() {
        return "TimeCore Special Resources";
    }

    @Override
    public void close() {

    }
}