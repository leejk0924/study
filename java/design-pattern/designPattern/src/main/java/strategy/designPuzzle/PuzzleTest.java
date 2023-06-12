package strategy.designPuzzle;

public class PuzzleTest {
    public static void main(String[] args) {
        Character character = new King();
        character.attack();
        Character queen = new Queen();
        queen.attack();
    }
}
