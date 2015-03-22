package logic;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractBox implements Box{
	
		protected List<Box> adjacentBoxes;
		protected String colour;
		protected boolean possibleMove;
		protected boolean marked;
		
		/* (non-Javadoc)
		 * @see logic.Box#getQuestion()
		 */
		/* (non-Javadoc)
		 * @see logic.Box#getQuestion()
		 */
		@Override
		public abstract Question getQuestion();
		
		/**
		public Box(String colour,Box ... args){
			adjacentBoxes = new ArrayList<>();
			for(Box b :args){
				this.adjacentBoxes.add(b);
			}
			this.colour=colour;
		}*/
		
		public AbstractBox(String colour){
			this.colour=colour;
		}
		
		/* (non-Javadoc)
		 * @see logic.Box#setAdjacents(logic.AbstractBox)
		 */
		@Override
		public void setAdjacents(Box ... args){
			adjacentBoxes = new ArrayList<>();
			for(Box b :args){
				this.adjacentBoxes.add(b);
			}
		}

		/* (non-Javadoc)
		 * @see logic.Box#getAdjacentBoxes()
		 */
		@Override
		public List<Box> getAdjacentBoxes() {
			return adjacentBoxes;
		}

		/* (non-Javadoc)
		 * @see logic.Box#setAdjacentBoxes(java.util.List)
		 */
		@Override
		public void setAdjacentBoxes(List<Box> adjacentBoxes) {
			this.adjacentBoxes = adjacentBoxes;
		}

		/* (non-Javadoc)
		 * @see logic.Box#getColour()
		 */
		@Override
		public String getColour() {
			return colour;
		}

		/* (non-Javadoc)
		 * @see logic.Box#setColour(java.lang.String)
		 */
		@Override
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
			AbstractBox other = (AbstractBox) obj;
			if (colour == null) {
				if (other.colour != null)
					return false;
			} else if (!colour.equals(other.colour))
				return false;
			return true;
		}

		/* (non-Javadoc)
		 * @see logic.Box#setPossibleMove(boolean)
		 */
		@Override
		public void setPossibleMove(boolean b) {
			this.possibleMove = b;
		}
		
		/* (non-Javadoc)
		 * @see logic.Box#getPossibleMove()
		 */
		@Override
		public boolean getPossibleMove(){
			return this.possibleMove;
		}

		/* (non-Javadoc)
		 * @see logic.Box#isMarked()
		 */
		@Override
		public boolean isMarked() {
			return marked;
		}
		
		/* (non-Javadoc)
		 * @see logic.Box#mark(boolean)
		 */
		@Override
		public void mark(boolean marked){
			this.marked = marked;
		}
		
		
		
}
