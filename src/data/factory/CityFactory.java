package data.factory;

import data.City;
import data.Data;

public class CityFactory extends DataFactory {

	@Override
	protected Data getData() {
		return new City(this.getList());
	}

}
