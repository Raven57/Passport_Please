package data.factory;

import data.Data;
import data.Name;

public class NameFactory extends DataFactory {

	@Override
	protected Data getData() {
		return new Name(this.getList());
	}
}
