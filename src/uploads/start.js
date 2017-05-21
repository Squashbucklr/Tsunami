module.exports.handle = function(v){
    
    v.bot.user.setStatus('online');
    v.bot.user.setUsername('BaristaBot');
    v.core.log(v, 'info', 'I am ready!');
    v.bot.user.setGame("Espresso Simulator");
    var quo = v.localStorage.getItem("json");
    var sch = v.localStorage.getItem("sche");
    var sts = v.localStorage.getItem("stats");
    var mid = v.localStorage.getItem("mids");
    var usr = v.localStorage.getItem("users");
    if(quo == null){ v.localStorage.setItem("json", "\"data\":[]"); quo = v.localStorage.getItem("json"); }
    if(sch == null){ v.localStorage.setItem("sche", "\"schs\":[]"); sch = v.localStorage.getItem("sche"); }
    if(sts == null){ v.localStorage.setItem("stats", "\"all\":[]"); v.localStorage.getItem("stats"); }
    if(mid == null){ v.localStorage.setItem("mids", "\"all\":[]"); v.localStorage.getItem("mids"); }
    if(usr == null){ v.localStorage.setItem("users", "\"ids\":[]"); v.localStorage.getItem("users"); }
    v.core.log(v, 'info', quo);
    v.core.log(v, 'info', sch);
    v.core.log(v, 'info', mid);
    v.quotes = JSON.parse(quo);
    v.scheds = JSON.parse(sch);
    v.stats = JSON.parse(sts);
    v.users = JSON.parse(usr);
    v.messageIds = JSON.parse(mid);
    v.core.handleNewUsers(v);

//    for(var i = 0; i < v.stats.all.length; i++){
//        console.log(v.stats.all[i].discord);
//    }
    for(var i = 0; i < v.scheds.schs.length; i++){
//        v.schedHandle.schedule1(scheds.schs[i]);
    }
    //RUNS EVERY 10 MINS
//    var daily = schedule.scheduleJob('*/5 * * * *', function(){
    var tenmins = v.schedule.scheduleJob('0,5,10,15,20,25,30,35,40,45,50,55 * * * *', function(){
        v.statHandle.list(v);
    });
    var onemin = v.schedule.scheduleJob('* * * * *', function(){
        v.core.handleNewUsers(v);
    });
    var hourly = v.schedule.scheduleJob('1 * * * *', function(){
        v.core.log(v, 'warn', 'UPDATING STATS, this may cause bot downtime');
        v.b = true;
        for(var i = 0; i < v.stats.all.length; i++){ 
            v.statHandle.update(v, v.stats.all[i]);
        }
    });
    v.b = true;
    v.statHandle.list(v);
    
//    v.statsHandle.list();
//    for(var i = 0; i < stats.all.length; i++){   
//        update(stats.all[i]);
//    }
}