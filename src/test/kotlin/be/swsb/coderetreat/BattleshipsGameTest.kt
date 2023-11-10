package be.swsb.coderetreat

import be.swsb.coderetreat.Direction.Horizontal
import be.swsb.coderetreat.Player.Player1
import be.swsb.coderetreat.Player.Player2
import be.swsb.coderetreat.ShipType.Destroyer
import org.assertj.core.api.Assertions.assertThat
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
}

