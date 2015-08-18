package org.confetti.util;

public class Tuple<T1, T2> {
	private final T1 mT1;
	private final T2 mT2;

	public Tuple(T1 t1, T2 t2) {
		mT1 = t1;
		mT2 = t2;
	}
	
	public T1 getFirst() {
		return mT1;
	}

	public T2 getSecond() {
		return mT2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mT1 == null) ? 0 : mT1.hashCode());
		result = prime * result + ((mT2 == null) ? 0 : mT2.hashCode());
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
		Tuple<?, ?> other = (Tuple<?, ?>) obj;
		if (mT1 == null) {
			if (other.mT1 != null)
				return false;
		} else if (!mT1.equals(other.mT1))
			return false;
		if (mT2 == null) {
			if (other.mT2 != null)
				return false;
		} else if (!mT2.equals(other.mT2))
			return false;
		return true;
	}


}
