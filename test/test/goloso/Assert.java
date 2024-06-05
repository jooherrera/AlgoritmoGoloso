package test.goloso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import app.model.Vertice;

public class Assert {

	public static void iguales(ArrayList<Vertice> arrayList, ArrayList<Vertice> arrayList2){
		assertEquals(arrayList.size(), arrayList2.size());
		
		for(int i = 0 ; i < arrayList.size(); i++) {
			assertTrue(arrayList2.contains(arrayList.get(i)));
		}
	}
}