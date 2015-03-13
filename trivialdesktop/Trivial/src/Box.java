import java.util.ArrayList;
import java.util.List;


public class Box {
	
		private List<Box> adjacentBoxes;
		private String colour;
		private boolean possibleMove;
		private boolean marked;
		
		/**
		public Box(String colour,Box ... args){
			adjacentBoxes = new ArrayList<>();
			for(Box b :args){
				this.adjacentBoxes.add(b);
			}
			this.colour=colour;
		}*/
		
		public Box(String colour){
			this.colour=colour;
		}
		
		public void setAdjacents(Box ... args){
			adjacentBoxes = new ArrayList<>();
			for(Box b :args){
				this.adjacentBoxes.add(b);
			}
		}

		public List<Box> getAdjacentBoxes() {
			return adjacentBoxes;
		}

		public void setAdjacentBoxes(List<Box> adjacentBoxes) {
			this.adjacentBoxes = adjacentBoxes;
		}

		public String getColour() {
			return colour;
		}

		public void setColour(String colour) {
			this.colour = colour;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((colour == null) ? 0 : colour.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Box other = (Box) obj;
			if (colour == null) {
				if (other.colour != null)
					return false;
			} else if (!colour.equals(other.colour))
				return false;
			return true;
		}

		public void setPossibleMove(boolean b) {
			this.possibleMove = b;
		}
		
		public boolean getPossibleMove(){
			return this.possibleMove;
		}

		public boolean isMarked() {
			return marked;
		}
		
		public void mark(boolean marked){
			this.marked = marked;
		}
		
		
		
}
