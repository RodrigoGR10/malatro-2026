package cl.uchile.dcc

import controller.GameController
import exceptions.InvalidTransitionException
import joker.*
import munit.FunSuite
import pinta.*
import rango.*

class GameControllerTest extends FunSuite {
  private def straightFlushHand(): Hand =
    new Hand(
      List(
        new Card(Two, Diamonds),
        new Card(Three, Diamonds),
        new Card(Four, Diamonds),
        new Card(Five, Diamonds),
        new Card(Six, Diamonds)
      ),
      List(GreedyJoker, DeviousJoker)
    )

  test("controller starts in PreGame state") {
    val controller = new GameController(new Hand())

    assert(controller.state.isPreGame)
    assertEquals(controller.totalScore, 0)
    assertEquals(controller.targetScore, 1000)
    assertEquals(controller.resultMessage, "Game has not started.")
  }

  test("startGame moves controller to PlayerTurn") {
    val controller = new GameController(new Hand())

    controller.startGame()

    assert(controller.state.isPlayerTurn)
    assertEquals(controller.resultMessage, "Game in progress. Total score: 0.")
  }

  test("playHand scores the played cards") {
    val hand = straightFlushHand()
    val controller = new GameController(hand)

    controller.startGame()
    val playScore = controller.playHand(List(0, 1, 2, 3, 4))

    assertEquals(playScore, 5060)
    assertEquals(controller.lastPlayScore, 5060)
    assertEquals(controller.totalScore, 5060)
  }

  test("playing before starting the game is invalid") {
    val controller = new GameController(straightFlushHand())

    intercept[InvalidTransitionException] {
      controller.playHand(List(0))
    }
  }

  test("discardHand works during PlayerTurn") {
    val hand = new Hand(
      List(
        new Card(Two, Hearts),
        new Card(Three, Diamonds),
        new Card(Four, Spades)
      )
    )
    val controller = new GameController(hand)

    controller.startGame()
    val discarded = controller.discardHand(List(0, 2))

    assertEquals(discarded, List(new Card(Two, Hearts), new Card(Four, Spades)))
    assertEquals(hand.cards, List(new Card(Three, Diamonds)))
  }

  test("third play moves the game to RoundEnd and finishes with loss") {
    val hand = new Hand(
      List(
        new Card(Two, Hearts),
        new Card(Three, Hearts),
        new Card(Four, Hearts)
      )
    )
    val controller = new GameController(hand, 1000)

    controller.startGame()
    controller.playHand(List(0))
    controller.playHand(List(0))
    controller.playHand(List(0))

    assert(controller.state.isRoundEnd)
    assert(controller.isFinished)
    assert(controller.hasLost)
    assert(!controller.hasWon)
    assertEquals(controller.totalScore, 24)
    assertEquals(controller.resultMessage, "Game finished: only 24 of 1000 points reached. You lose.")
  }

  test("third play finishes with win when target score is reached") {
    val hand = new Hand(
      List(
        new Card(Two, Diamonds),
        new Card(Three, Diamonds),
        new Card(Four, Diamonds),
        new Card(Five, Diamonds),
        new Card(Six, Diamonds),
        new Card(Two, Hearts),
        new Card(Three, Hearts)
      ),
      List(GreedyJoker, DeviousJoker)
    )
    val controller = new GameController(hand, 1000)

    controller.startGame()
    controller.playHand(List(0, 1, 2, 3, 4))
    controller.playHand(List(0))
    controller.playHand(List(0))

    assert(controller.state.isRoundEnd)
    assert(controller.isFinished)
    assert(controller.hasWon)
    assert(!controller.hasLost)
    assert(controller.totalScore >= 1000)
    assert(controller.resultMessage.contains("You win!"))
  }
}