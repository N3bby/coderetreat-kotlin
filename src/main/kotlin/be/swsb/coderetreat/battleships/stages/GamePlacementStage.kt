package be.swsb.coderetreat.battleships.stages

import be.swsb.coderetreat.battleships.GameBoard
import be.swsb.coderetreat.battleships.Player
import be.swsb.coderetreat.battleships.Ship
import be.swsb.coderetreat.battleships.math.Location

class GamePlacementStage(gameBoard: GameBoard) : GameStage(gameBoard) {

    override fun placeShip(player: Player, ship: Ship): GameStage {
        val updatedGameBoard = gameBoard.placeShip(player, ship)
        return if(updatedGameBoard.areAllShipsPlaced()) {
            GameBattleStage(updatedGameBoard)
        } else {
            GamePlacementStage(updatedGameBoard)
        }
    }

    override fun shoot(targetPlayer: Player, location: Location): GameStage {
        throw IllegalStateException("Game is not in the battle stage yet")
    }

}