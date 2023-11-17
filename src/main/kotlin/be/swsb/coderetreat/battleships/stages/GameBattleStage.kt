package be.swsb.coderetreat.battleships.stages

import be.swsb.coderetreat.battleships.GameBoard
import be.swsb.coderetreat.battleships.Player
import be.swsb.coderetreat.battleships.Player.Player1
import be.swsb.coderetreat.battleships.Ship
import be.swsb.coderetreat.battleships.Shot.Hit
import be.swsb.coderetreat.battleships.math.Location

class GameBattleStage(gameBoard: GameBoard, private val activePlayer: Player = Player1) : GameStage(gameBoard) {

    override fun placeShip(player: Player, ship: Ship): GameStage {
        throw IllegalStateException("Cannot place ships while battling")
    }

    override fun shoot(targetPlayer: Player, location: Location): GameStage {
        if(activePlayer == targetPlayer) {
            throw IllegalStateException("It is currently $activePlayer's turn")
        }
        val (updatedGameBoard, shot) = gameBoard.shoot(targetPlayer, location)
        return if(shot is Hit) {
            if(updatedGameBoard.isGameFinished()) {
                GameFinishedStage(updatedGameBoard)
            } else {
                GameBattleStage(updatedGameBoard, activePlayer)
            }
        } else {
            GameBattleStage(updatedGameBoard, getNextPlayer())
        }
    }

    private fun getNextPlayer(): Player {
        val nextPlayerIndex = (Player.entries.indexOf(activePlayer) + 1) % Player.entries.size
        return Player.entries[nextPlayerIndex]
    }

}