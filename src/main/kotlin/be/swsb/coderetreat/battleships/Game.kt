package be.swsb.coderetreat.battleships

import be.swsb.coderetreat.battleships.Player.Player1
import be.swsb.coderetreat.battleships.Player.Player2
import be.swsb.coderetreat.battleships.math.Bounds
import be.swsb.coderetreat.battleships.math.Location
import java.lang.IllegalArgumentException

enum class Player {
    Player1,
    Player2
}

class BattleshipsGame {

    private val bounds = Bounds(0, 0, 9, 9)
    private val fields = mutableMapOf(
        Pair(Player1, Field(bounds = bounds)),
        Pair(Player2, Field(bounds = bounds))
    )

    private fun getField(player: Player) =
        fields[player] ?: throw IllegalArgumentException("No field exists for player: $player")

    fun placeShip(player: Player, ship: Ship) {
        val field = getField(player)
        fields[player] = field.addShip(ship)
    }

    fun render(player: Player): String {
        val renderOutput = StringBuilder()
        val field = getField(player)
        for (y in field.bounds.minY..field.bounds.maxY) {
            for (x in field.bounds.minX..field.bounds.maxX) {
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
