package pl.lukasznowicki.carservice.dto;

public class RocordRange {

	private int from;
	private int to;

	public RocordRange(int from, int to) {
		super();
		this.from = from;
		this.to = to;
	}

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

}
