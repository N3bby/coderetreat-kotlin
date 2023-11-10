package be.swsb.coderetreat

import be.swsb.coderetreat.Player.Player1
import be.swsb.coderetreat.Player.Player2
import java.lang.IllegalArgumentException

enum class Player {
    Player1,
    Player2
}

enum class ShipType(val length: Int) {
    Carrier(5),
    Battleship(4),
    Destroyer(3),
    Submarine(3),
    PatrolBoat(2),
}

enum class Direction(val directionVector: Vector) {
    Horizontal(Vector(1, 0)),
    Vertical(Vector(0, 1))
}

data class Location(val x: Int, val y: Int) {
    operator fun plus(vector: Vector): Location {
        return Location(x + vector.x, y + vector.y)
    }
}
data class Vector(val x: Int, val y: Int) {
    operator fun times(scalar: Int): Vector {
        return Vector(x * scalar, y * scalar)
    }
}

data class Ship(val location: Location, val direction: Direction, val type: ShipType) {
    fun getLocations(): List<Location> {
        return (0..type.length).map { index -> location + direction.directionVector * index }
    }
}

data class BattleshipsField(val ships: List<Ship>) {
    fun isShipAtLocation(location: Location): Boolean {
        return ships.any { it.getLocations().contains(location) }
    }
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
