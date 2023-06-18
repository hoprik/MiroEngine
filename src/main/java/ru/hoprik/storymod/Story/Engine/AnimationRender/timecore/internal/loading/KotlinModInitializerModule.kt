package ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.internal.loading

import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.reflection.KReflectionHelper.qualifiedName
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.api.registry.util.AutoRegistrable
import ru.hoprik.storymod.Story.Engine.AnimationRender.Timecore.internal.loading.ModInitializer.ParentableField
import java.util.stream.Stream
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.javaField

object KotlinModInitializerModule {
    fun handlesEntriesAnno(containerClass: Class<*>): Boolean {
        return containerClass.kotlin.objectInstance != null
    }

    fun processEntriesAnno(containerClass: Class<*>): Stream<ParentableField> {
        val kclass = containerClass.kotlin
        if (kclass.objectInstance != null) {
            return kclass.declaredMemberProperties.stream()
                .map {
                    it.javaField
                        ?: throw IllegalArgumentException("${AutoRegistrable.Entries::class.java.simpleName} is applied to property without backing field (${it.qualifiedName}).")
                }
                .filter { ModInitializer.validateFieldForEntriesAnno(it) }
                .map { ParentableField.withParent(it, kclass.objectInstance) }
        }

        return Stream.empty()
    }
}