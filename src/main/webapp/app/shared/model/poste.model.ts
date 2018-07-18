export interface IPoste {
    id?: number;
    name?: string;
    quotePart?: number;
    placeName?: string;
    placeId?: number;
    habilitationName?: string;
    habilitationId?: number;
}

export class Poste implements IPoste {
    constructor(
        public id?: number,
        public name?: string,
        public quotePart?: number,
        public placeName?: string,
        public placeId?: number,
        public habilitationName?: string,
        public habilitationId?: number
    ) {}
}
