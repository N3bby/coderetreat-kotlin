package be.swsb.coderetreat.battleships

import be.swsb.coderetreat.battleships.math.Direction
import be.swsb.coderetreat.battleships.math.Location
import be.swsb.coderetreat.battleships.stages.GameStage
import be.swsb.coderetreat.battleships.stages.createNewGame

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
fun createDefaultGame(): GameStage {
    return createNewGame()
        // Player 1
        .placeShip(Player.Player1, Ship(Location(1, 1), Direction.Horizontal, ShipType.Destroyer))
        .placeShip(Player.Player1, Ship(Location(8, 1), Direction.Vertical, ShipType.Carrier))
        .placeShip(Player.Player1, Ship(Location(1, 4), Direction.Vertical, ShipType.Battleship))
        .placeShip(Player.Player1, Ship(Location(4, 4), Direction.Horizontal, ShipType.PatrolBoat))
        .placeShip(Player.Player1, Ship(Location(4, 7), Direction.Horizontal, ShipType.Submarine))
        // Player 2
        .placeShip(Player.Player2, Ship(Location(1, 1), Direction.Horizontal, ShipType.Destroyer))
        .placeShip(Player.Player2, Ship(Location(8, 1), Direction.Vertical, ShipType.Carrier))
        .placeShip(Player.Player2, Ship(Location(1, 4), Direction.Vertical, ShipType.Battleship))
        .placeShip(Player.Player2, Ship(Location(4, 4), Direction.Horizontal, ShipType.PatrolBoat))
        .placeShip(Player.Player2, Ship(Location(4, 7), Direction.Horizontal, ShipType.Submarine))
}

/**
 * Creates a game where Player 2 has this field layout:
 * 🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
 * 🟦🔥🔥🔥🟦🟦🟦🟦🔥🟦
 * 🟦🟦🟦🟦🟦🟦🟦🟦🔥🟦
 * 🟦🟦🟦🟦🟦🟦🟦🟦🔥🟦
 * 🟦🔥🟦🟦🔥🔥🟦🟦🔥🟦
 * 🟦🔥🟦🟦🟦🟦🟦🟦🔥🟦
 * 🟦🔥🟦🟦🟦🟦🟦🟦🟦🟦
 * 🟦🔥🟦🟦🔥🔥🔥🟦🟦🟦
 * 🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
 * 🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
 */
fun createFinishedGame(): GameStage {
    val game = createDefaultGame()

    // Creating copy of ships from createDefaultGame here
    // because I don't want to expose Fleets from the Game
    val player2Ships = listOf(
        Ship(Location(1, 1), Direction.Horizontal, ShipType.Destroyer),
        Ship(Location(8, 1), Direction.Vertical, ShipType.Carrier),
        Ship(Location(1, 4), Direction.Vertical, ShipType.Battleship),
        Ship(Location(4, 4), Direction.Horizontal, ShipType.PatrolBoat),
        Ship(Location(4, 7), Direction.Horizontal, ShipType.Submarine)
    )

    return player2Ships
        .flatMap { it.getLocations() }
        .fold(game) {
            acc: GameStage,
            location: Location -> acc.shoot(Player.Player2, location)
        }
}