// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500
package VehicleStats;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VehicleLock implements Lock {
	
	Vehicle vehicleRef;
	ReentrantLock vehicleLock;
	public VehicleLock(Vehicle vehicle)
	{
		this.vehicleRef = vehicle;
		vehicleLock = new ReentrantLock();
	}
	public Vehicle getVehicle() {return vehicleRef;}
	@Override
	public void lock() {
		vehicleLock.lock();
		
	}
	@Override
	public void lockInterruptibly() throws InterruptedException {
		vehicleLock.lockInterruptibly();
		
	}
	@Override
	public boolean tryLock() {
		return tryLock();
		
	}
	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return tryLock(time,unit);
	}
	@Override
	public void unlock() {
		vehicleLock.unlock();
		
	}
	@Override
	public Condition newCondition() {
		return vehicleLock.newCondition();
	}
	
	

}
