package at.neonartworks.neolib.math.number;

public class NeoNumberInt implements INeoNumber<Integer> {
	private int value;

	public NeoNumberInt(int value) {
		this.value = value;
	}

	public NeoNumberInt(String s) {
		int parse;
		try{
			parse = Integer.parseInt(s);
		}
		catch(NumberFormatException e){
			
		}
	}

	public NeoNumberInt(INeoNumber<?> other) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public INeoNumber<Integer> add(INeoNumber<?> other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public INeoNumber<Integer> multiply(INeoNumber<?> other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public INeoNumber<Integer> divide(INeoNumber<?> other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public INeoNumber<?> divideAccurate(INeoNumber<?> other, int accuracy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public INeoNumber<Integer> abs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer leftToTop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer leftToBottom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer max() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer min() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public INeoNumber<Integer> valueZero() {
		return null;
	}

	@Override
	public int getAccurateDecimals() {
		return 0;
	}

	@Override
	public boolean hasDecimals() {
		return false;
	}

	@Override
	public Class<?> getType() {
		return Integer.class;
	}

	@Override
	public Sign getSign() {
		return value >= 0 ? Sign.PLUS : Sign.MINUS;
	}

}
