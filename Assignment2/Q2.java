@Test
public void testCoverage() {
    assertEquals(2, coverage(2, 1, 2, 1));
    assertEquals(-2, coverage(1, 2, 1, 2));
    assertEquals(1, coverage(2, 1, 1, 2));
    assertEquals(0, coverage(0, -1, 1, 2));
    assertEquals(0, coverage(1, 2, 2, 1));
}