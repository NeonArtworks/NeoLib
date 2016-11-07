package at.neonartworks.neolib.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * A Variable which has Multiple Values and can be used as a normal Variable
 * 
 * @author Alexander Daum
 *
 */
public class Multiplication extends AbstractVariable<String> {
	private final List<IVariable<?>> val;
	private final String name;

	public Multiplication(String id, List<IVariable<?>> values) {
		this.name = id;
		this.val = new ArrayList<IVariable<?>>(values);
	}

	public Multiplication(String id, IVariable<?>... values) {
		this.name = id;
		this.val = Arrays.asList(values);
	}

	public Multiplication(String id, double...values) {
		this.name = id;
		List<IVariable<?>> temp = new ArrayList<IVariable<?>>(values.length);
		for(int i = 0; i < values.length; i ++){
			temp.add(new Variable(this.name + "_" + i,values[i]));
		}
		this.val = temp;
	}

	public static Multiplication createFromDoubleList(String id, List<Double> values) {
		List<IVariable<?>> temp = new ArrayList<IVariable<?>>(values.size());
		for(int i = 0; i < values.size(); i ++){
			temp.add(new Variable(id + "_" + i,values.get(i)));
		}
		return new Multiplication(id, temp);
	}

	@Override
	public String getIdentifier() {
		return name;
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

}
