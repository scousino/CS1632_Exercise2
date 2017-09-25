import static org.junit.Assert.*;

import org.junit.*;

public class LaboonCoinTest {

    // Re-usable LaboonCoin reference.
    LaboonCoin _l;

    // Create a new LaboonCoin instance before each test.
    @Before
    public void setup() {
	_l = new LaboonCoin();
    }
    
    // Assert that creating a new LaboonCoin instance
    // does not return a null reference
    @Test
    public void testLaboonCoinExists() {
	assertNotNull(_l);
    }

    // Creating a block String with valid data should return
    // a valid block.  This includes printing data out
    // normally, the previous hash and current hash as a hex
    // representations of themself, and the nonce in hex
    // repretentation.  Values should be delimited by
    // pipes.
    @Test
    public void testCreateBlockValid() {
	int prevHash = 0x0;
	int nonce = 0x16f2;
	int hash = 0x545ac;
	String validBlock = _l.createBlock("kitten", prevHash, nonce, hash);
	assertEquals("kitten|00000000|000016f2|000545ac", validBlock);
    }

    // Trying to represent a blockchain which has no blocks
    // in it should just return an empty string.
    @Test
    public void testGetBlockChainNoElements() {
	// By default, _l.blockchain has no elements in it.
	// So we can just test it immediately.
	String blockChain = _l.getBlockChain();
	assertEquals("", blockChain);
    }
    

    // Viewing the blockchain as a full string which has valid
    // elements should include all of the elements.  Note that the
    // final element DOES have a trailing \n!
    @Test
    public void testGetBlockChainElements() {
	_l.blockchain.add("TESTBLOCK1|00000000|000010e9|000a3cd8");
	_l.blockchain.add("TESTBLOCK2|000a3cd8|00002ca8|0008ff30");
	_l.blockchain.add("TESTBLOCK3|0008ff30|00002171|0009f908");
	String blockChain = _l.getBlockChain();
	assertEquals("TESTBLOCK1|00000000|000010e9|000a3cd8\nTESTBLOCK2|000a3cd8|00002ca8|0008ff30\nTESTBLOCK3|0008ff30|00002171|0009f908\n", blockChain);
    }
	    
    // TODO - PUT YOUR SIX TESTS HERE

    // hash() tests start here
    @Test
    public void testLaboonHash() {
        String data = "laboon";
        int hashVal = _l.hash(data);
        assertEquals(hashVal, 1313179606);

    }

    @Test
    public void testEmptyHash() {
        String data = "";
        int hashVal = _l.hash(data);
        assertEquals(hashVal, 10000000);
    }

    @Test
    public void testLongAsciiNullHash() {
        String data = "\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0";
        int hashVal = _l.hash(data);
        assertEquals(hashVal, 0);
    }

    @Test
    public void testValidHashDifficulty3A() {
        // 0x000fd98a is a known good value, 3 zeroes
        int i = 0x000fd98a;
        assertTrue(_l.validHash(3, i));
    }

    @Test
    public void testValidHashDifficulty3B() {
        // 0x000000d4 is a known good value, 6 zeroes
        int i = 0x000000d4;
        assertTrue(_l.validHash(3, i));
    }

    @Test
    public void testInvalidHashDifficulty3A() {
        // 0x098ab873 is a known bad value, 1 zero
        int i = 0x098ab873;
        assertFalse(_l.validHash(3, i));
    }

    @Test
    public void testInvalidHashDifficulty3B() {
        // 0xab000000 is a known bad value, 1 zero
        int i = 0xab000000;
        assertFalse(_l.validHash(3, i));
    }

}
