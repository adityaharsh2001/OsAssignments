    // Write a program to implement First Come First Serve Scheduling Algorithm. 
    // Take at least 4 processes with their arrival times and execution times. 
    //  Your program must give as an output: the average waiting time and the average turn around time. 


    //Author: Aditya Harsh
    //Language: Javascript


    const processes = [
        {arrivalTime: 0, executionTime: 3},
        {arrivalTime: 1, executionTime: 2},
        {arrivalTime: 2, executionTime: 1},
        {arrivalTime: 3, executionTime: 4}
    ];
    (async () => {
        const averageWaitingTime = await getAverageWaitingTime(processes);
        const averageTurnAroundTime = await getAverageTurnAroundTime(processes);
        //print the data in a table
        console.table(processes);
        console.log(`Average waiting time: ${averageWaitingTime}`);
        console.log(`Average turn around time: ${averageTurnAroundTime}`);
    }
    )();
    //get average waiting time
    async function getAverageWaitingTime(processes) {
        const waitingTimes = await getWaitingTimes(processes);
        const totalWaitingTime = waitingTimes.reduce((total, waitingTime) => total + waitingTime);
        return totalWaitingTime / processes.length;
    }
    //get average turn around time
    async function getAverageTurnAroundTime(processes) {
        const turnAroundTimes = await getTurnAroundTimes(processes);
        const totalTurnAroundTime = turnAroundTimes.reduce((total, turnAroundTime) => total + turnAroundTime);
        return totalTurnAroundTime / processes.length;
    }
    //get waiting times
    async function getWaitingTimes(processes) {
        const waitingTimes = [];
        let totalWaitingTime = 0;
        for (let i = 0; i < processes.length; i++) {
            waitingTimes.push(totalWaitingTime);
            totalWaitingTime += processes[i].executionTime;
        }
        return waitingTimes;
    }
    //get turn around times
    async function getTurnAroundTimes(processes) {
        const turnAroundTimes = [];
        let totalTurnAroundTime = 0;
        for (let i = 0; i < processes.length; i++) {
            totalTurnAroundTime += processes[i].executionTime;
            turnAroundTimes.push(totalTurnAroundTime);
        }
        return turnAroundTimes;
    }




// Language: javascript