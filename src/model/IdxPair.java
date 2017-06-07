package model;

class IdxPair {
	final int begin;
	final int end;
	final int distance;

	IdxPair(int begin, int end){
		this.begin = begin;
		this.end = end;
		this.distance = end - begin;
	}

	int distance(){
		return this.distance;
	}

	int mid(){
		return this.end / 2;
	}
}