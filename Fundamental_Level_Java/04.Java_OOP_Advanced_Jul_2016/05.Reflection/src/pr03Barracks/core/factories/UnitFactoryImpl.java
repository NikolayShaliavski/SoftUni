package pr03Barracks.core.factories;

import pr03Barracks.contracts.Unit;
import pr03Barracks.contracts.UnitFactory;

import java.lang.reflect.Constructor;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME =
			"pr03Barracks.models.units.";

	@Override
	public Unit createUnit(String unitType) throws ReflectiveOperationException {

		Class<Unit> unitClass = (Class<Unit>) Class.forName(UNITS_PACKAGE_NAME + unitType);
		Constructor unitCtor = unitClass.getConstructor();
        Unit unit = (Unit) unitCtor.newInstance();
        return unit;
	}
}
