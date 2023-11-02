type MemberBoxProps = {
  imgName: string;
  name: string;
  position: string;
};

function MemberBox({ imgName, name, position }: MemberBoxProps) {
  const imgUrl = process.env.PUBLIC_URL + "/imgs/" + imgName + ".png";

  return (
    <div className="">
      <img src={imgUrl} alt={name} className="h-24 mb-6"/>
      <div className="text-center">
        <div className="text-s mb-4">{name}</div>
        <div>{position}</div>
      </div>
    </div>
  );
}

export default MemberBox;
