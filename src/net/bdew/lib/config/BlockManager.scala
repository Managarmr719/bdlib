/*
 * Copyright (c) bdew, 2013 - 2014
 * https://github.com/bdew/bdlib
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * https://raw.github.com/bdew/bdlib/master/MMPL-1.0.txt
 */

package net.bdew.lib.config

import net.minecraft.item.ItemStack
import net.minecraft.block.Block
import cpw.mods.fml.common.registry.GameRegistry
import net.bdew.lib.block.HasTE
import net.bdew.lib.Misc

class BlockManager(val ids: IdManager, addOldTileNames: Boolean = true) {

  def this(ids: IdManager) = this(ids, true)

  def regBlockCls[T <: Block](blockCls: Class[T], name: String, addStack: Boolean = true): T = {
    val block = blockCls.getConstructor(classOf[Int]).newInstance(ids.getBlockId(name): Integer)
    regBlock[T](block, name, addStack)
  }

  def regBlock[T <: Block](block: T, name: String, addStack: Boolean = true): T = {
    GameRegistry.registerBlock(block, name)

    if (addStack)
      GameRegistry.registerCustomItemStack(name, new ItemStack(block))

    if (block.isInstanceOf[HasTE[_]])
      if (addOldTileNames)
        GameRegistry.registerTileEntityWithAlternatives(block.asInstanceOf[HasTE[_]].TEClass,
          "%s.%s".format(Misc.getActiveModId, name), name)
      else
        GameRegistry.registerTileEntity(block.asInstanceOf[HasTE[_]].TEClass,
          "%s.%s".format(Misc.getActiveModId, name))

    return block
  }

  def load() {}
}
