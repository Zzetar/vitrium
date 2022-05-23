package domain;


import java.util.List;

public class AlbaranCompleto {
	private Albaran albaran;
	private List<LinAlb> list ;

	public AlbaranCompleto() {	}

	public Albaran getAlbaran() {
		return albaran;
	}

	public void setAlbaran(Albaran albaran) {
		this.albaran = albaran;
	}

	public List<LinAlb> getList() {
		return list;
	}

	public void setList(List<LinAlb> list) {
		this.list = list;
	}

}
