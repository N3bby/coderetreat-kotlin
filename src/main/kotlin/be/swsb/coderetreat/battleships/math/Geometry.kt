package be.swsb.coderetreat.battleships.math

enum class Direction(val directionVector: Vector) {
    Horizontal(Vector(1, 0)),
    Vertical(Vector(0, 1))
}

data class Bounds(val minX: Int, val minY: Int, val maxX: Int, val maxY: Int) {
    fun isWithinBounds(location: Location): Boolean {
        return location.x >= minX && location.y >= minY && location.x <= maxX && location.y <= maxY
    }
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