export interface ISample {
  id?: string;
  title?: string;
  deadline?: Date;
  active?: boolean;
  children?: ISampleChild[];
}

export interface ISampleChild {
  id?: string;
  joinedTime?: Date;
  active?: boolean;
}

export class Sample implements ISample {
  constructor(public id?: string, public title?: string, public deadline?: Date, public active?: boolean, public children?: ISampleChild[]) {}
}

export class SampleChild implements ISampleChild {
  constructor(public id?: string, public joinedTime?: Date, public active?: boolean) {}
}
