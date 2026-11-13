
package me.jetblack.examples.shared;

import org.junit.Test;
import static org.junit.Assert.*;

public class CountryTest {
    
    @Test
    public void testCountryGettersAndSetters() {
        Country country = new Country();
        country.setId(1);
        country.setName("Test Country");
        
        assertEquals(Integer.valueOf(1), country.getId());
        assertEquals("Test Country", country.getName());
    }

    @Test
    public void testCountryNullValues() {
        Country country = new Country();
        assertNull(country.getId());
        assertNull(country.getName());
    }

    @Test
    public void testRegionGettersAndSetters() {
        Region region = new Region();
        region.setId(1);
        region.setName("Test Region");
        region.setCountryId(100);

        assertEquals(Integer.valueOf(1), region.getId());
        assertEquals("Test Region", region.getName());
        assertEquals(Integer.valueOf(100), region.getCountryId());
    }

    @Test
    public void testRegionToString() {
        Region region = new Region();
        region.setId(1);
        region.setName("Test Region");
        region.setCountryId(100);

        String expected = "Region{id=1, name='Test Region', countryId=100}";
        assertEquals(expected, region.toString());
    }
}
