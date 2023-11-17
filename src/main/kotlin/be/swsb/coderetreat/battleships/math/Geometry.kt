package be.swsb.coderetreat.battleships.math

enum class Direction(val directionOffset: Offset) {
    Horizontal(Offset(1, 0)),
    Vertical(Offset(0, 1))
}

data class Bounds(val minX: Int, val minY: Int, val maxX: Int, val maxY: Int) {
    fun isWithinBounds(location: Location): Boolean {
        return location.x >= minX && location.y >= minY && location.x <= maxX && location.y <= maxY
    }
}

data class Location(val x: Int, val y: Int) {
    operator fun plus(offset: Offset): Location {
        return Location(x + offset.x, y + offset.y)
    }
}

data class Offset(val x: Int, val y: Int) {
    operator fun times(scalar: Int): Offset {
        return Offset(x * scalar, y * scalar)
    }
}