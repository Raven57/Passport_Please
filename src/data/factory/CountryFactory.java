package data.factory;

import data.Country;
import data.Data;

public class CountryFactory extends DataFactory {

	@Override
	protected Data getData() {
		return new Country(this.getList());
	}
}
