**start = 0
**end = 1
**statistics = statistics class, tracks stats


Algorithm #1

move(i, location)	{
	base case: covered() which checks if interval's covered
	if (left)	{
		moveLeft(i) : move left sensor at index i
		move(i, right)
	else {
		moveRight(length-1-i) : move right sensor at index (length-1)-i
		move(i+1, left)
	}		
}

covered()	{
	make a copy of the sensor array
	sort it in ascending x coordinate order
	go through the array 2 elements at a time,
		if the 1st element's x coordinate + it's range < 2nd element's x coordinate - it's range
		return false
		else check next 2 elements
}

moveLeft(i)	{
	if(i=0)	{
		sensor[i].x = start + sensor[i].range
	}
	else	{
		sensor[i].x = sensor[i-1].x + 2*sensor[i].range
	}
	statistics.movementsum++
}

moveRight(i)	{
	if(i=length-1)	{
		sensor[i].x = end - sensor[i].range
	}
	else	{
		sensor[i].x = sensor[i+1].x - 2*sensor[i].range
	}
	statistics.movementsum++
}

Algorithm #2

move2()	{
	for(each sensor in the array)	{
		if(sensor[i].x = (2*(i+1)-1)/(2n))	return
		sensor[i].x = (2*(i+1)-1)/(2n))
		statistics.movementsum++
	}		
}

