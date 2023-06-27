package com.idtech.entity;

import com.idtech.customMobAI.*;
import com.idtech.entity.HerobrineGOAP.HerobrineAgent;
import com.idtech.entity.HerobrineGOAP.HerobrineUnit;
import com.javagoap.src.javaGOAP.GoapAction;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.util.math.AxisAlignedBB;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

import static com.idtech.BaseMod.printInfo;

public class Herobrine extends EntityMob{


    public int energy; //used to perform actions. also replenishes over time
    public List<EntityMob> mobAllies; // all mobs under herobrine's control
    public float doThingTimer = 40;
    public float energyTimer = 20;
    private HerobrineUnit goapUnit = new HerobrineUnit(this);
    private HerobrineAgent goapAgent = new HerobrineAgent(goapUnit, this);

    public Queue<GoapAction> currentPlan;
    public GoapAction currentAction = null;

    Vec3d lookVector = this.getLook(1);
    int MAX_RANGE = 30;

    public Herobrine(World world){
        super(world);
        energy = 100;
        //getMobs();
    }

    public void getMobs(){
        Minecraft mc = Minecraft.getMinecraft();
        AxisAlignedBB searchRadius = new AxisAlignedBB(100, 100, 100, -100, -100, -100);
        List<Entity> allEntities = mc.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(30));
        printInfo(allEntities.toString());
    }

    /* Plan: If there is a walkable path to player, use that
    * If player is on a similar elevation but is obscured by blocks, use explosive tactics
    * How to send creepers to correct position?
    * Impossible to make them dig a hole upwards, since there's only so high they can go above the
    * ground.
    * Could have them move forward in direction of player, then explode when next to a wall
    * If getting a list of all blocks, get pos of first block (plus some offset) then send creeper
    * to that location
    * Or, without needing to list all blocks, use the same incremental measurement to determine how far ahead the
    * creeper needs to move
    * */
    public boolean getPathToPlayer(){
        if(this.getAttackTarget() != null){
            Path pathToPlayer = getNavigator().getPathToEntityLiving(this.getAttackTarget());

            //Check if player is above herobrine
            //Check if player is obscured by blocks
            int blocksToPlayer = allBlocksInLine();
            //May include air blocks depending on how close HB and the player are to the wall between them
            printInfo(blocksToPlayer + " away from player");

            if(pathToPlayer != null) {
                return true;
            }
        }
        return false;
    }

    public BlockPos firstBlockBetweenPlayer(){
        if(this.getAttackTarget() != null){
            Vec3d HBpos = this.getPositionVector();
            Vec3d PlrPos = this.getAttackTarget().getPositionVector();

            RayTraceResult raytrace = world.rayTraceBlocks(HBpos, PlrPos, false);
            if(raytrace != null){
                Vec3d hit = raytrace.hitVec;
                BlockPos goToPos = new BlockPos(raytrace.hitVec); //Hopefully this returns the block in front the hitvec, and not the one inside the hitvec
                return goToPos;
            }
        }
        printInfo("Can't get 1st block.");
        return null;
    }

    public BlockPos nextBlockBetweenPlayer(BlockPos start)
    {
        if(this.getAttackTarget() != null){
            Vec3d HBpos = this.getPositionVector();
            Vec3d PlrPos = this.getAttackTarget().getPositionVector();
            Vec3d diff = PlrPos.subtract(HBpos);
            Vec3d increment = diff.scale(1/diff.lengthVector());

            Vec3d start_moddified = new Vec3d(start.getX(), start.getY(), start.getZ());
            start_moddified.add(increment);
            start_moddified.add(increment);
            return new BlockPos(start_moddified);
        }
        return new BlockPos(0, 0, 0);
    }

    public int allBlocksInLine(){ //Exact position of each entity in the world
        if(this.getAttackTarget() != null){
            Vec3d start = this.getPositionVector();
            Vec3d stop = this.getAttackTarget().getPositionVector();

            int blocks = 0;
            Vec3d diff = stop.subtract(start);
            //printInfo("Distance length: " + diff.lengthVector());
            //The distance is actually fairly similar to length in blocks, although it would be problematic diagonally

            Vec3d increment = diff.scale(0.25/diff.lengthVector());
            //printInfo("Increment length: " + increment.lengthVector());

            BlockPos lastBlockPos = new BlockPos(start);
            Vec3d currentPos = start.add(increment);
            //printInfo("Current pos:" + currentPos);
            for(double i = increment.lengthVector(); i < diff.lengthVector(); i += increment.lengthVector()){
                BlockPos blockPos = new BlockPos(currentPos);
                //printInfo("Current blockPos: " + blockPos);
                if(!blockPos.equals(lastBlockPos)){
                    blocks += 1;
                    lastBlockPos = blockPos;
                }
                currentPos = currentPos.add(increment);
                //printInfo("Current pos:" + currentPos);
            }
            return blocks;
        }
        return 0;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(500.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(100.0D); //Max follow range
        this.isImmuneToFire = true;
        this.isImmuneToExplosions();
    }

    @Override
    protected void initEntityAI(){
        //this.tasks.addTask(0, new SpawnMob(this, "zombie"));
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, false));
        //this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
    }

    @Override
    public void onLivingUpdate(){
        super.onLivingUpdate();
        //goapAgent.update();
        //Restores 1 energy every 1 seconds. Caps at 100
        if(currentPlan != null && !currentPlan.isEmpty() && currentAction == null){
            doPlanAction(currentPlan.remove());
        }

        energyTimer -= 1;
        if(energyTimer <= 0) {
            energy += 1;
            if (energy > 100) {
                energy = 100;
            }
            energyTimer = 20;
        }
        doThingTimer -= 1;
        if(doThingTimer <= 0){
            if(!getPathToPlayer() && getAttackTarget() != null) {
                currentPlan = goapAgent.getPlan();
            }
            else{
                printInfo("Can reach player");
            }
            doThingTimer = 40;
        }
    }

    public void doPlanAction(GoapAction action){
        if(action.toString().startsWith("com.idtech.entity.HerobrineGOAP.actions.ActionSpawnCreeper")){
            printInfo("Spawning creeper");
            this.tasks.addTask(0, new SpawnMob(this, "creeper"));
            this.currentAction = action;
        }
        else{
            printInfo("Can't do this action. Moving on");
        }
    }


    public List<EntityMob> getAllies(){
        return mobAllies;
    }

    public int getEnergy(){
        return energy;
    }

    public void removeEnergy(int reduction){
        this.energy -= reduction;
    }

    // called in MyEvents when creeper explodes
    public void creeperExploded(EntityCreeper e) {

    }

    //Action "Listeners"
    //These will get called to remove the associated action from the task list
    //Called at the END of each custom AI even
    //May need to do special check
    public void doneSpawn(EntityMob mobSpawned){
        //mobAllies.add(mobSpawned);
        for (Object a : this.tasks.taskEntries.toArray())
        {
            EntityAIBase ai = ((EntityAITasks.EntityAITaskEntry) a).action;
            //if done action then remove
            if(ai instanceof SpawnMob){
                this.tasks.removeTask(ai);
                currentAction = null;
            }
        }
    }


    public void doneSend(EntityMob e){
        for (Object a : e.tasks.taskEntries.toArray())
        {
            EntityAIBase ai = ((EntityAITasks.EntityAITaskEntry) a).action;
            //if done action then remove
            if(ai instanceof MobFollowPlayer){
                e.tasks.removeTask(ai);
            }
        }
    }

    public void doneAttack(EntityMob e){
        for (Object a : e.tasks.taskEntries.toArray())
        {
            EntityAIBase ai = ((EntityAITasks.EntityAITaskEntry) a).action;
            //if done action then remove
            if(ai instanceof MobAttackPlayer){
                e.tasks.removeTask(ai);
            }
        }
    }

    //remove task from multiple lists
    public void doneAttackMobs(EntityMob[] es){
        for (EntityMob e : es) {
            for (Object a : e.tasks.taskEntries.toArray())
            {
                EntityAIBase ai = ((EntityAITasks.EntityAITaskEntry) a).action;
                //if done action then remove
                if(ai instanceof MobAttackPlayer){
                    e.tasks.removeTask(ai);
                }
            }
        }
    }

    public void doneSendMobs(EntityMob[] es){
        for (EntityMob e : es) {
            for (Object a : e.tasks.taskEntries.toArray()) {
                EntityAIBase ai = ((EntityAITasks.EntityAITaskEntry) a).action;
                //if done action then remove
                if (ai instanceof MobsFollowPlayer) {
                    e.tasks.removeTask(ai);
                }
            }
        }
    }

    public void doneAction(EntityMob e){
        for (Object a : this.targetTasks.taskEntries.toArray())
        {
            EntityAIBase ai = ((EntityAITasks.EntityAITaskEntry) a).action;
            //if done action then remove
            if(ai instanceof SpecialAction){
                this.targetTasks.removeTask(ai);
            }
        }
    }


    //TEAM ACTION
    //think about later
}
