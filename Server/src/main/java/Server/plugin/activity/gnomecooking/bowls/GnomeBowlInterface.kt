package plugin.activity.gnomecooking.bowls

import core.game.component.Component
import core.game.component.ComponentDefinition
import core.game.component.ComponentPlugin
import core.game.content.ItemNames
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import plugin.skill.Skills

@InitializablePlugin
class GnomeBowlInterface : ComponentPlugin() {

    override fun handle(player: Player?, component: Component?, opcode: Int, button: Int, slot: Int, itemId: Int): Boolean {
        player    ?: return false
        component ?: return false

        when(button){
            3 -> attemptMake(PreparedProduct.HALF_MADE_WOR_HO,player)
            12 -> attemptMake(PreparedProduct.HALF_MADE_VEG_BA,player)
            21 -> attemptMake(PreparedProduct.HALF_MADE_TAN_TO,player)
            34 -> attemptMake(PreparedProduct.HALF_MADE_CHOC_B,player)
        }
        return true
    }

    private fun attemptMake(bowl: PreparedProduct, player: Player){
        if(!player.inventory.containsItem(Item(ItemNames.GNOME_SPICE_2169)) && (bowl != PreparedProduct.HALF_MADE_CHOC_B) ){
            player.dialogueInterpreter.sendDialogue("You need gnome spices for this.")
            return
        }

        if(player.skills.getLevel(Skills.COOKING) < bowl.levelReq){
            player.dialogueInterpreter.sendDialogue("You don't have the needed level to make this.")
            return
        }

        var hasAll = true
        for(item in bowl.requiredItems){
            if(!player.inventory.containsItem(item)){
                hasAll = false
                break
            }
        }

        if(!hasAll){
            player.dialogueInterpreter.sendDialogue("You don't have all the ingredients needed for this.")
            return
        }

        player.inventory.remove(*bowl.requiredItems)
        player.inventory.remove(Item(ItemNames.HALF_BAKED_BOWL_2177))
        player.inventory.add(Item(bowl.product))
        player.interfaceManager.close()
    }

    internal enum class PreparedProduct(val product: Int,val levelReq: Int, val requiredItems: Array<Item>){
        HALF_MADE_CHOC_B(9558,42, arrayOf(Item(ItemNames.CHOCOLATE_BAR_1973,4),Item(ItemNames.EQUA_LEAVES_2128))),
        HALF_MADE_TAN_TO(9559,40, arrayOf(Item(ItemNames.TOADS_LEGS_2152,4),Item(ItemNames.CHEESE_1985,2),Item(ItemNames.DWELLBERRIES_2126),Item(ItemNames.EQUA_LEAVES_2128,2))),
        HALF_MADE_VEG_BA(9561,35, arrayOf(Item(ItemNames.POTATO_1942,2),Item(ItemNames.ONION_1957,2))),
        HALF_MADE_WOR_HO(9563,30, arrayOf(Item(ItemNames.KING_WORM_2162,4),Item(ItemNames.ONION_1957,2)))
    }

    override fun newInstance(arg: Any?): Plugin<Any> {
        ComponentDefinition.put(435,this)
        return this
    }

}