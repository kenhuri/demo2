import { IBureau } from 'app/shared/model//bureau.model';

export interface IPersonne {
    id?: number;
    name?: string;
    client?: string;
    imageContentType?: string;
    image?: any;
    personnes?: IBureau[];
}

export class Personne implements IPersonne {
    constructor(
        public id?: number,
        public name?: string,
        public client?: string,
        public imageContentType?: string,
        public image?: any,
        public personnes?: IBureau[]
    ) {}
}
