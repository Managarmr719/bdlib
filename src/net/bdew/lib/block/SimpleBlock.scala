/*
 * Copyright (c) bdew, 2013 - 2014
 * https://github.com/bdew/bdlib
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://bdew.net/minecraft-mod-public-license/
 */

package net.bdew.lib.block

import net.bdew.lib.Misc
import net.minecraft.block.Block
import net.minecraft.block.material.Material

import scala.language.implicitConversions

trait NamedBlock extends Block {
  def name: String
}

class SimpleBlock(val name: String, material: Material) extends Block(material) with NamedBlock {
  val modId = Misc.getActiveModId
  setRegistryName(modId, name)
}
