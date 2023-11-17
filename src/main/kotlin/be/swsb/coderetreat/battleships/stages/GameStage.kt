package be.swsb.coderetreat.battleships.stages

import be.swsb.coderetreat.battleships.GameBoard
import be.swsb.coderetreat.battleships.Player
import be.swsb.coderetreat.battleships.Ship
import be.swsb.coderetreat.battleships.math.Location

abstract class GameStage(protected val gameBoard: GameBoard) {

    abstract fun placeShip(player: Player, ship: Ship): GameStage
    abstract fun shoot(targetPlayer: Player, location: Location): GameStage

    fun render(player: Player): String {
        return gameBoard.render(player)
    }

}

fun createNewGame(): GamePlacementStage {
    return GamePlacementStage(GameBoard.newGameBoard())
}