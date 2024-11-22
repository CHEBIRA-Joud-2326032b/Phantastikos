import org.example.creatures.HommeBete;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreatureTest {

    @Test
    void attendreTest(){
        HommeBete elf = new HommeBete("Prout", 'Z', 78, 187, 256);
        elf.attendre();
        assertEquals(70, elf.getMoral());
    }

    @Test
    void triageTest(){

    }
}
