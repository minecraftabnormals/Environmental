package com.team_abnormals.environmental.core.registry;

import java.lang.reflect.InvocationTargetException;

import com.google.common.collect.ImmutableSet;
import com.team_abnormals.environmental.core.Environmental;

import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.SoundEvents;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EnvironmentalVillagers
{
	public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, Environmental.MODID);
	public static final DeferredRegister<PointOfInterestType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, Environmental.MODID);

	public static final RegistryObject<PointOfInterestType> KILN 	= POI_TYPES.register("kiln", () -> new PointOfInterestType("ceramist", PointOfInterestType.getAllStates(EnvironmentalBlocks.KILN.get()), 1, 1));
	public static final RegistryObject<PointOfInterestType> SAWMILL = POI_TYPES.register("sawmill", () -> new PointOfInterestType("carpenter", PointOfInterestType.getAllStates(EnvironmentalBlocks.SAWMILL.get()), 1, 1));
	
	public static final RegistryObject<VillagerProfession> CERAMIST 	= PROFESSIONS.register("ceramist", () -> new VillagerProfession("ceramist", KILN.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_BUTCHER));
	public static final RegistryObject<VillagerProfession> CARPENTER 	= PROFESSIONS.register("carpenter", () -> new VillagerProfession("carpenter", SAWMILL.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_MASON));

	public static void setupVillagers() {
		try {
			ObfuscationReflectionHelper.findMethod(PointOfInterestType.class, "func_221052_a", PointOfInterestType.class).invoke(null, KILN.get());
			ObfuscationReflectionHelper.findMethod(PointOfInterestType.class, "func_221052_a", PointOfInterestType.class).invoke(null, SAWMILL.get());
		} catch(IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}