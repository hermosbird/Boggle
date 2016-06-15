import static org.junit.Assert.*;
import org.junit.Test;

public class BoggleTrayTest {

  private char[][] tray = {   // Always use upper case letters in the dice tray
      {'A', 'B', 'C', 'D' }, 
      {'E', 'F', 'G', 'H' },
      {'I', 'J', 'K', 'L' }, 
      {'M', 'N', 'O', 'P' } };

  @Test
  public void testStringFindWhenThereStartingInUpperLeftCorner() {
    BoggleTray bt = new BoggleTray(tray);
    assertTrue(bt.foundInBoggleTray("ABC"));  
    assertTrue(bt.foundInBoggleTray("abC"));  // Must be case insensitive
    assertTrue(bt.foundInBoggleTray("aBf"));
    assertTrue(bt.foundInBoggleTray("abc"));
    assertTrue(bt.foundInBoggleTray("ABCD"));
    // ... 
    assertTrue(bt.foundInBoggleTray("ABFEJINM"));
    assertTrue(bt.foundInBoggleTray("AbCdHgFeIjKLpONm"));
    assertTrue(bt.foundInBoggleTray("ABCDHLPOKJNMIEFG"));
  }

  @Test
  public void testStringFindWhenNotThere () {
    BoggleTray bt = new BoggleTray(tray);
    assertFalse(bt.foundInBoggleTray("acb"));
    assertFalse(bt.foundInBoggleTray("AiE"));
    assertFalse(bt.foundInBoggleTray("AiE"));
    assertTrue(bt.foundInBoggleTray("AEi"));
    // ... 
  }

  @Test
  public void testStringFindWhenAttemptIsMadeToUseALetterTwice () {
    BoggleTray bt = new BoggleTray(tray);
    assertFalse(bt.foundInBoggleTray("ABA"));
    assertFalse(bt.foundInBoggleTray("ABB"));
    assertFalse(bt.foundInBoggleTray("aEa"));
    // ... 
  }


  // More tests will be necessary

}