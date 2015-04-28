package br.com.alerta_online.entidades_assaltos;


public class Assalto {
	
		private int id_assalto, id_email,id_local_assalto;
		private String sexo_vitima, descricao_assalto, tipo_assalto, hora_assalto, data_assalto;
		
		
		public Assalto ()
		
		{
			
		}
		
		public Assalto(int id_assalto, int id_email,
				int id_local_assalto,
			    String sexo_vitima,String tipo_assalto,
				String hora_assalto, String data_assalto) {
			super();
			this.id_assalto = id_assalto;
			this.id_email = id_email;
			this.id_local_assalto = id_local_assalto;
			this.sexo_vitima = sexo_vitima;
			this.tipo_assalto = tipo_assalto;
			this.hora_assalto = hora_assalto;
			this.data_assalto = data_assalto;
		}
	

		public int getId_assalto() {
			return id_assalto;
		}

		public void setId_assalto(int id_assalto) {
			this.id_assalto = id_assalto;
		}

		public int getId_email() {
			return id_email;
		}

		public void setId_email(int id_email) {
			this.id_email = id_email;
		}

		public int getId_local_assalto() {
			return id_local_assalto;
		}

		public void setId_local_assalto(int id_local_assalto) {
			this.id_local_assalto = id_local_assalto;
		}

		public String getSexo_vitima() {
			return sexo_vitima;
		}

		public void setSexo_vitima(String sexo_vitima) {
			this.sexo_vitima = sexo_vitima;
		}

		public String getDescricao_assalto() {
			return descricao_assalto;
		}

		public void setDescricao_assalto(String descricao_assalto) {
			this.descricao_assalto = descricao_assalto;
		}

		public String getTipo_assalto() {
			return tipo_assalto;
		}

		public void setTipo_assalto(String tipo_assalto) {
			this.tipo_assalto = tipo_assalto;
		}

		public String getHora_assalto() {
			return hora_assalto;
		}

		public void setHora_assalto(String hora_assalto) {
			this.hora_assalto = hora_assalto;
		}

		public String getData_assalto() {
			return data_assalto;
		}

		public void setData_assalto(String data_assalto) {
			this.data_assalto = data_assalto;
		}

		@Override
		public String toString() {
			return "Assalto [id_assalto=" + id_assalto + ", id_email="
					+ id_email + ", id_local_assalto=" + id_local_assalto
					+ ", sexo_vitima=" + sexo_vitima + ", descricao_assalto="
					+ descricao_assalto + ", tipo_assalto=" + tipo_assalto
					+ ", hora_assalto=" + hora_assalto + ", data_assalto="
					+ data_assalto + "]";
		}
	
}

