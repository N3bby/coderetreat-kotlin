package be.swsb.coderetreat.battleships

import be.swsb.coderetreat.battleships.Player.Player1
import be.swsb.coderetreat.battleships.Player.Player2
import be.swsb.coderetreat.battleships.ShipType.*
import be.swsb.coderetreat.battleships.math.Direction.Horizontal
import be.swsb.coderetreat.battleships.math.Direction.Vertical
import be.swsb.coderetreat.battleships.math.Location
import be.swsb.coderetreat.battleships.stages.GameFinishedStage
import be.swsb.coderetreat.battleships.stages.createNewGame
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class GameTest {

    @Test
    fun `new BattleshipsGame - field should be empty for both players`() {
        val game = createNewGame()

        val expected = """
            🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
            🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
            🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
            🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
            🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
            🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
            🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
            🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
            🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
            🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
        """.trimIndent()
        assertThat(game.render(Player1)).isEqualTo(expected)
        assertThat(game.render(Player2)).isEqualTo(expected)
    }

    @Nested
    inner class PlaceShipTest {
        @Test
        fun `placeShip - player should be able to place a ship at a location horizontally`() {
            val game = createNewGame()
                .placeShip(Player1, Ship(Location(1, 2), Horizontal, Destroyer))

            val expected = """
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🚢🚢🚢🟦🟦🟦🟦🟦🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
            """.trimIndent()
            assertThat(game.render(Player1)).isEqualTo(expected)
        }

        @Test
        fun `placeShip - player should be able to place a ship at a location vertically`() {
            val game = createNewGame()
                .placeShip(Player1, Ship(Location(1, 2), Vertical, Destroyer))

            val expected = """
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🚢🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🚢🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🚢🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
            """.trimIndent()
            assertThat(game.render(Player1)).isEqualTo(expected)
        }

        @Test
        fun `placeShip - should throw exception if ship is placed out of bounds`() {
            val game = createNewGame()

            assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
                game.placeShip(Player1, Ship(Location(-1, 0), Horizontal, Destroyer))
            }.withMessage("Ships must be placed within the bounds of the field")

            assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
                game.placeShip(Player1, Ship(Location(0, -1), Vertical, Destroyer))
            }.withMessage("Ships must be placed within the bounds of the field")

            assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
                game.placeShip(Player1, Ship(Location(8, 0), Horizontal, Destroyer))
            }.withMessage("Ships must be placed within the bounds of the field")

            assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
                game.placeShip(Player1, Ship(Location(0, 8), Vertical, Destroyer))
            }.withMessage("Ships must be placed within the bounds of the field")
        }

        @Test
        fun `placeShip - should throw exception if ships overlap`() {
            val game = createNewGame()
                .placeShip(Player1, Ship(Location(0, 2), Horizontal, Carrier))

            assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
                game.placeShip(Player1, Ship(Location(2, 0), Vertical, Battleship))
            }.withMessage("Ships may not overlap")
        }

        @Test
        fun `placeShip - should throw exception if player places the same type of ship multiple times`() {
            val game = createNewGame()
                .placeShip(Player1, Ship(Location(0, 0), Horizontal, Battleship))

            assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
                game.placeShip(Player1, Ship(Location(0, 1), Horizontal, Battleship))
            }.withMessage("You may only place each type of ship once")
        }
    }

    @Nested
    inner class ShootTest {
        @Test
        fun `shoot - should hit when location overlaps a ship`() {
            val game = createDefaultGame()
                .shoot(Player2, Location(1,1))

            val expected = """
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🔥🚢🚢🟦🟦🟦🟦🚢🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🚢🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🚢🟦
                🟦🚢🟦🟦🚢🚢🟦🟦🚢🟦
                🟦🚢🟦🟦🟦🟦🟦🟦🚢🟦
                🟦🚢🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🚢🟦🟦🚢🚢🚢🟦🟦🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
            """.trimIndent()
            assertThat(game.render(Player2)).isEqualTo(expected)
        }

        @Test
        fun `shoot - should miss when location overlaps a ship`() {
            val game = createDefaultGame()
                .shoot(Player2, Location(1,0))

            val expected = """
                🟦💨🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🚢🚢🚢🟦🟦🟦🟦🚢🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🚢🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🚢🟦
                🟦🚢🟦🟦🚢🚢🟦🟦🚢🟦
                🟦🚢🟦🟦🟦🟦🟦🟦🚢🟦
                🟦🚢🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🚢🟦🟦🚢🚢🚢🟦🟦🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
            """.trimIndent()
            assertThat(game.render(Player2)).isEqualTo(expected)
        }

        @Test
        fun `shoot - should throw if shot is not within bounds`() {
            val game = createDefaultGame()

            assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
                game.shoot(Player2, Location(-1, 0))
            }.withMessage("Shot is not within bounds of the field")
        }

        @Test
        fun `shoot - should throw if location was already shot`() {
            val game = createDefaultGame()
                .shoot(Player2, Location(1, 1))

            assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
                game.shoot(targetPlayer = Player2, Location(1, 1))
            }.withMessage("This location was already shot")
        }
    }

    @Nested
    inner class GameStateTest {
        @Test
        fun `placeShip - should throw exception when all ships are already placed`() {
            val game = createDefaultGame()

            assertThatExceptionOfType(IllegalStateException::class.java).isThrownBy {
                game.placeShip(Player1, Ship(Location(0, 0), Horizontal, PatrolBoat))
            }.withMessage("Cannot place ships while battling")
        }

        @Test
        fun `shoot - should throw exception when not all ships are placed yet`() {
            val game = createNewGame()

            assertThatExceptionOfType(IllegalStateException::class.java).isThrownBy {
                game.shoot(Player1, Location(0, 0))
            }.withMessage("Game is not in the battle stage yet")
        }

        @Test
        fun `shoot - should allow Player 1 to shoot first`() {
            val game = createDefaultGame()

            assertThatCode {
                game.shoot(targetPlayer = Player2, Location(0, 0))
            }.doesNotThrowAnyException()
        }

        @Test
        fun `shoot - should switch to the next player's turn if they miss`() {
            val game = createDefaultGame()

            assertThatCode {
                game
                    .shoot(targetPlayer = Player2, Location(0, 0))
                    .shoot(targetPlayer = Player1, Location(0, 0))
            }.doesNotThrowAnyException()
        }

        @Test
        fun `shoot - should not switch to the next player's turn if they hit`() {
            val game = createDefaultGame()

            assertThatCode {
                game
                    .shoot(targetPlayer = Player2, Location(1, 1))
                    .shoot(targetPlayer = Player2, Location(0, 0))
                    .shoot(targetPlayer = Player1, Location(0, 0))
            }.doesNotThrowAnyException()
        }

        @Test
        fun `shoot - should throw exception when trying to shoot your own board`() {
            val game = createDefaultGame()

            assertThatExceptionOfType(IllegalStateException::class.java).isThrownBy {
                game.shoot(targetPlayer = Player1, Location(0, 0))
            }.withMessage("It is currently Player1's turn")
        }

        @Test
        fun `isGameFinished - should return true when one player has sunk all their opponent's ships`() {
            val game = createFinishedGame()

            assertThat(game.render(Player2)).isEqualTo("""
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🔥🔥🔥🟦🟦🟦🟦🔥🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🔥🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🔥🟦
                🟦🔥🟦🟦🔥🔥🟦🟦🔥🟦
                🟦🔥🟦🟦🟦🟦🟦🟦🔥🟦
                🟦🔥🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🔥🟦🟦🔥🔥🔥🟦🟦🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
                🟦🟦🟦🟦🟦🟦🟦🟦🟦🟦
            """.trimIndent())
            assertThat(game).isInstanceOf(GameFinishedStage::class.java)
        }

        @Test
        fun `shoot - should throw exception when game is finished`() {
            val game = createFinishedGame()

            assertThatExceptionOfType(IllegalStateException::class.java).isThrownBy {
                game.shoot(targetPlayer = Player2, Location(0, 0))
            }.withMessage("Game is finished")
        }
    }

}

