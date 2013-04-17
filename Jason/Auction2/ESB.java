public class ESB {
	
	private StringBuilder sb;
	private String end;
	public ESB(String start){
		sb = new StringBuilder(start).append("(");
		end = ")";
	}
	
	public ESB(String start, String end){
		sb = new StringBuilder(start);
		this.end = end;
	}
	
	public String insert(Object... terms) {
		for(Object term : terms){
			sb.append(term).append(",");
		}
		sb.replace(sb.length() - 1, sb.length(), end);
		return sb.toString();
	}

	
}
