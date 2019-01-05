export default [
  {
    path:'/building/building-DevelopRewardOrPunish',
    models:['building/buildingDevelopRewardOrPunish'],
    isExact:true,
    component:()=>import('../routes/building/developrewardorpunish/DevelopRewardOrPunishIndex'),
  },
  {
    path:'/building/building-RewardOrPunish',
    models:['building/buildingRewardPunish'],
    isExact:true,
    component:()=>import('../routes/building/rewardorpunish/RewardOrPunishIndex'),
  },
  {
    path: '/building/building-developer',
    models: ['building/buildingDeveloper'],
    isExact: true,
    component: () => import('../routes/building/developer/BuildingDeveloperIndex'),
  },
  {
    path: '/building/building-site',
    models: ['building/buildingSite'],
    isExact: true,
    component: () => import('../routes/building/site/BuildingSiteIndex'),
  },
  {
    path: '/building/work-type',
    models: ['building/workType'],
    isExact: true,
    component: () => import('../routes/building/worktype/WorkTypeIndex'),
  },
  {
    path: '/building/employee',
    models: ['building/employee'],
    isExact: true,
    component: () => import('../routes/building/employee/EmployeeIndex'),
  },
  {
    path: '/building/attence-device',
    models: ['building/attenceDevice'],
    isExact: true,
    component: () => import('../routes/building/attencedevice/AttenceDeviceIndex'),
  },
  {
    path: '/building/developer-deposit',
    models: ['building/deposit'],
    isExact: true,
    component: () => import('../routes/building/deposit/DepositIndex'),
  },
  {
    path: '/building/salary-for-developer',
    models: ['building/salaryForDeveloper'],
    isExact: true,
    component: () => import('../routes/building/salary/SalaryForDeveloper'),
  },
  {
    path: '/building/salary-for-supervisor',
    models: ['building/salaryForManager'],
    isExact: true,
    component: () => import('../routes/building/salary/SalaryForManager'),
  },
  {
    path: '/building/manual-clocking',
    models: ['building/manualClocking'],
    isExact: true,
    component: () => import('../routes/building/clocking/ManualClocking'),
  },
  {
    path: '/building/attence-record',
    models: ['building/attenceRecord'],
    isExact: true,
    component: () => import('../routes/building/attencerecord/AttenceRecord'),
  },
  {
    path: '/building/attence-record-manager',
    models: ['building/attenceRecordManager'],
    isExact: true,
    component: () => import('../routes/building/attencerecord/AttenceRecordManager'),
  },

  {
    path: '/building/time-setting',
    models: ['building/clockingSetting'],
    isExact: true,
    component: () => import('../routes/building/clocking/Setting'),
  },
];
