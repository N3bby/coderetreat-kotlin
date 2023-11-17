package be.swsb.coderetreat.battleships

import be.swsb.coderetreat.battleships.math.Direction
import be.swsb.coderetreat.battleships.math.Location

/**
 * Creates a game where both players have this field layout:
 * 🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
 * 🟦🚢🚢🚢🟦🟦🟦🟦🚢🟦
 * 🟦🟦🟦🟦🟦🟦🟦🟦🚢🟦
 * 🟦🟦🟦🟦🟦🟦🟦🟦🚢🟦
 * 🟦🚢🟦🟦🚢🚢🟦🟦🚢🟦
 * 🟦🚢🟦🟦🟦🟦🟦🟦🚢🟦
 * 🟦🚢🟦🟦🟦🟦🟦🟦🟦🟦
 * 🟦🚢🟦🟦🚢🚢🚢🟦🟦🟦
 * 🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
 * 🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
 */
fun createDefaultGame(): Game {
    val game = Game()

    game.placeShip(Player.Player1, Ship(Location(1, 1), Direction.Horizontal, ShipType.Destroyer))
    game.placeShip(Player.Player1, Ship(Location(8, 1), Direction.Vertical, ShipType.Carrier))
    game.placeShip(Player.Player1, Ship(Location(1, 4), Direction.Vertical, ShipType.Battleship))
    game.placeShip(Player.Player1, Ship(Location(4, 4), Direction.Horizontal, ShipType.PatrolBoat))
    game.placeShip(Player.Player1, Ship(Location(4, 7), Direction.Horizontal, ShipType.Submarine))

    game.placeShip(Player.Player2, Ship(Location(1, 1), Direction.Horizontal, ShipType.Destroyer))
    game.placeShip(Player.Player2, Ship(Location(8, 1), Direction.Vertical, ShipType.Carrier))
    game.placeShip(Player.Player2, Ship(Location(1, 4), Direction.Vertical, ShipType.Battleship))
    game.placeShip(Player.Player2, Ship(Location(4, 4), Direction.Horizontal, ShipType.PatrolBoat))
    game.placeShip(Player.Player2, Ship(Location(4, 7), Direction.Horizontal, ShipType.Submarine))

    return game
}