package codigoTresEnderecos;

import analisadorSintatico.tabela.TableEntry;

public class Operand {

		public String name;
		public Temp temporary;
		public TableEntry tableEntry;
		public int type;
		public Operand() {
			
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Temp getTemporary() {
			return temporary;
		}
		public void setTemporary(Temp temporary) {
			this.temporary = temporary;
		}
		public TableEntry getTableEntry() {
			return tableEntry;
		}
		public void setTableEntry(TableEntry tableEntry) {
			this.tableEntry = tableEntry;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		
}
