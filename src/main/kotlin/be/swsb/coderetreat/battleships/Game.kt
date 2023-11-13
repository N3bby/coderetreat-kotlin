package be.swsb.coderetreat.battleships

import be.swsb.coderetreat.battleships.Player.Player1
import be.swsb.coderetreat.battleships.Player.Player2
import be.swsb.coderetreat.battleships.math.Location
import java.lang.IllegalArgumentException

enum class Player {
    Player1,
    Player2
}

class BattleshipsGame {

    private val fields = mutableMapOf(
        Pair(Player1, BattleshipsField(emptyList())),
        Pair(Player2, BattleshipsField(emptyList()))
    )

    private fun getField(player: Player) =
        fields[player] ?: throw IllegalArgumentException("No field exists for player: $player")

    fun placeShip(player: Player, ship: Ship) {
        val field = getField(player)
        val updatedField = field.copy(ships = field.ships + listOf(ship))
        fields[player] = updatedField
    }

    fun render(player: Player): String {
        val renderOutput = StringBuilder()
        val field = getField(player)
        for (y in 0..<10) {
            for (x in 0..<10) {
                if (field.isShipAtLocation(Location(x, y))) {
                    renderOutput.append("🚢")
                } else {
                    renderOutput.append("🌊")
                }
            }
            renderOutput.append("\n")
        }
        return renderOutput.toString().trim()
    }
}
