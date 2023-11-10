package be.swsb.coderetreat

import be.swsb.coderetreat.Direction.Horizontal
import be.swsb.coderetreat.Direction.Vertical
import be.swsb.coderetreat.Player.Player1
import be.swsb.coderetreat.Player.Player2
import be.swsb.coderetreat.ShipType.*
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

class BattleshipsGameTest {

    @Test
    fun `new BattleshipsGame - field should be empty for both players`() {
        val battleshipsGame = BattleshipsGame()

        val player1FieldState: String = battleshipsGame.render(Player1)
        val player2FieldState: String = battleshipsGame.render(Player2)
        val expected = """
            🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
            🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
            🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
            🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
            🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
            🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
            🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
            🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
            🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
            🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
        """.trimIndent()
        assertThat(player1FieldState).isEqualTo(expected)
        assertThat(player2FieldState).isEqualTo(expected)
    }

    @Test
    fun `placeShip - player should be able to place a ship at a location horizontally`() {
        val battleshipsGame = BattleshipsGame()
        battleshipsGame.placeShip(Player1, Ship(Location(1, 2), Horizontal, Destroyer))

        val player1FieldState: String = battleshipsGame.render(Player1)
        val expected = """
            🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
            🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
            🌊🚢🚢🚢🚢🌊🌊🌊🌊🌊
            🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
            🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
            🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
            🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
            🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
            🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
            🌊🌊🌊🌊🌊🌊🌊🌊🌊🌊
        """.trimIndent()
        assertThat(player1FieldState).isEqualTo(expected)
    }

    @Test
    fun `placeShip - should throw exception if ship is placed out of bounds`() {
        val battleshipsGame = BattleshipsGame()

        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            battleshipsGame.placeShip(Player1, Ship(Location(-1, 0), Horizontal, Destroyer))
        }.withMessage("Ships must be placed within the bounds of the field")

        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            battleshipsGame.placeShip(Player1, Ship(Location(0, -1), Vertical, Destroyer))
        }.withMessage("Ships must be placed within the bounds of the field")

        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            battleshipsGame.placeShip(Player1, Ship(Location(8, 0), Horizontal, Destroyer))
        }.withMessage("Ships must be placed within the bounds of the field")

        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            battleshipsGame.placeShip(Player1, Ship(Location(0, 8), Vertical, Destroyer))
        }.withMessage("Ships must be placed within the bounds of the field")
    }

    @Test
    fun `placeShip - should throw exception if ships overlap`() {
        val battleshipsGame = BattleshipsGame()
        battleshipsGame.placeShip(Player1, Ship(Location(0, 2), Horizontal, Carrier))

        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            battleshipsGame.placeShip(Player1, Ship(Location(2, 0), Vertical, Battleship))
        }.withMessage("Ships may not overlap")
    }

    @Test
    fun `placeShip - should throw exception if player places the same type of ship multiple times`() {
        val battleshipsGame = BattleshipsGame()
        battleshipsGame.placeShip(Player1, Ship(Location(0, 0), Horizontal, Battleship))

        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            battleshipsGame.placeShip(Player1, Ship(Location(0, 1), Horizontal, Battleship))
        }.withMessage("You may only place each type of ship once")
    }
}

