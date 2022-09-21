/*
 * Copyright (c) 2022 TeamMoeg
 *
 * This file is part of Caupona.
 *
 * Caupona is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * Caupona is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * Specially, we allow this software to be used alongside with closed source software Minecraft(R) and Forge or other modloader.
 * Any mods or plugins can also use apis provided by forge or com.teammoeg.caupona.api without using GPL or open source.
 *
 * You should have received a copy of the GNU General Public License
 * along with Caupona. If not, see <https://www.gnu.org/licenses/>.
 */

package com.teammoeg.caupona.blocks.hypocaust;

import java.util.Random;

import com.teammoeg.caupona.blocks.CPHorizontalEntityBlock;
import com.teammoeg.caupona.client.Particles;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;

public abstract class BathHeatingBlock<V extends BathHeatingTile> extends CPHorizontalEntityBlock<V> {

	public BathHeatingBlock(RegistryObject<BlockEntityType<V>> te, Properties p_54120_) {
		super(te, p_54120_);
	}

	@Override
	public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, Random pRandom) {
		super.animateTick(pState, pLevel, pPos, pRandom);
		if (pRandom.nextDouble() < 0.05 && pLevel.getFluidState(pPos.above()).is(FluidTags.WATER)) {
			BlockEntity te = pLevel.getBlockEntity(pPos);
			if (te instanceof BathHeatingTile bath) {
				if (bath.getHeat() > 0) {
					pLevel.addParticle(Particles.STEAM.get(), pPos.getX() + pRandom.nextFloat(), pPos.getY() + 2,
							pPos.getZ() + pRandom.nextFloat(), 0.0D, 0.0D, 0.0D);
				}
			}
		}
	}

}
