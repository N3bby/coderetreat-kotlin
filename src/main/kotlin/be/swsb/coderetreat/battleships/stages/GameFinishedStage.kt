package be.swsb.coderetreat.battleships.stages

import be.swsb.coderetreat.battleships.GameBoard
import be.swsb.coderetreat.battleships.Player
import be.swsb.coderetreat.battleships.Ship
import be.swsb.coderetreat.battleships.math.Location

class GameFinishedStage(gameBoard: GameBoard) : GameStage(gameBoard) {

    override fun placeShip(player: Player, ship: Ship): GameStage {
        throw IllegalStateException("Game is finished")
    }

    override fun shoot(targetPlayer: Player, location: Location): GameStage {
        throw IllegalStateException("Game is finished")
    }

}