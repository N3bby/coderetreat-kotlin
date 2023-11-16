package be.swsb.coderetreat.battleships

import be.swsb.coderetreat.battleships.math.Bounds
import be.swsb.coderetreat.battleships.math.Direction
import be.swsb.coderetreat.battleships.math.Location

enum class ShipType(val length: Int) {
    Carrier(5),
    Battleship(4),
    Destroyer(3),
    Submarine(3),
    PatrolBoat(2),
}

data class Ship(val location: Location, val direction: Direction, val type: ShipType) {

    fun isWithinBounds(bounds: Bounds): Boolean {
        return getLocations().all { bounds.isWithinBounds(it) }
    }

    fun getLocations(): List<Location> {
        return (0..<type.length).map { index -> location + direction.directionVector * index }
    }
}