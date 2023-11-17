package be.swsb.coderetreat.battleships

import be.swsb.coderetreat.battleships.math.Bounds
import be.swsb.coderetreat.battleships.math.Location

enum class Player {
    Player1,
    Player2
}

data class GameBoard private constructor(private val bounds: Bounds, private val fields: Map<Player, Field>) {

    companion object {
        fun newGameBoard(bounds: Bounds = Bounds(0, 0, 9, 9)): GameBoard {
            return GameBoard(
                bounds = bounds,
                fields = Player.entries.associateWith { Field.emptyField(bounds) },
            )
        }
    }

    private fun getField(player: Player) =
        fields[player] ?: throw IllegalArgumentException("No field exists for player: $player")

    fun placeShip(player: Player, ship: Ship): GameBoard {
        val field = getField(player)
        val updatedField = field.addShip(ship)
        return copy(fields = fields + mapOf(player to updatedField))
    }

    fun areAllShipsPlaced(): Boolean {
        return fields.values.all { it.areAllShipsPlaced() }
    }

    fun shoot(targetPlayer: Player, location: Location): Pair<GameBoard, Shot> {
        val field = getField(targetPlayer)
        val (updatedField, shot) = field.shoot(location)
        return Pair(
            copy(fields = fields + mapOf(targetPlayer to updatedField)),
            shot
        )
    }

    fun isGameFinished(): Boolean {
        return fields.values.any { it.areAllShipsSunk() }
    }

    fun render(player: Player): String {
        val renderOutput = StringBuilder()
        val field = getField(player)
        for (y in field.bounds.minY..field.bounds.maxY) {
            for (x in field.bounds.minX..field.bounds.maxX) {
                val location = Location(x, y)
                if (field.isHitAtLocation(location)) {
                    renderOutput.append("🔥")
                } else if (field.isMissAtLocation(location)) {
                    renderOutput.append("💨")
                } else if (field.isShipAtLocation(location)) {
                    renderOutput.append("🚢")
                } else {
                    renderOutput.append("🟦")
                }
            }
            renderOutput.append("\n")
        }
        return renderOutput.toString().trim()
    }
}
